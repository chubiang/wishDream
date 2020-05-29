import 'date-fns';
import React, { Component, useEffect, useRef, useState, createContext, Fragment } from 'react'
import Axios from 'axios'
import { makeStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import { Form, Field, reduxForm } from 'redux-form'
import RenderCheckbox  from '../Component/RenderCheckbox'
import Constants from '../services/constants'
import { TextField, Select, ListSubheader, MenuItem, FormControl, Grid, InputLabel } from '@material-ui/core'
import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
} from '@material-ui/pickers';

const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: '100%',
  },
}));

function WithPetForm(props) {
	const classes = useStyles();

	const [topBreed, setTopBreed] = React.useState([]);
	const [subBreed, setSubBreed] = React.useState([]);
	const [selectedTopBreed, setSelectedTopBreed] =  React.useState({ id: '', name: '' });
	const [selectedSubBreed, setSelectedSubBreed] =  React.useState({ id: '', name: '' });
	const [petInfo, setPetInfo] = React.useState({});

	useEffect(() => {
		
		if (!topBreed.length) {
			Axios.get(Constants.Url.pet.breed)
			.then( res => {
					setTopBreed(res.data);
			});
		}
		if (props.petInfo) {
			props.petInfo.petBreedId = selectedSubBreed.id;
		}
		console.log(props);
	})
	
	async const changeBreedId = (event) => {
		if (event.target.value) {
			setSelectedTopBreed({ id: event.target.value, name: event.target.innerText});

			await Axios.get(Constants.Url.pet.breed + "/" + event.target.value)
			 .then( res => {
				setSubBreed(res.data);
			});
		}
	}

	const changeSubBreedId = (event) => {
		if (event.target.value) {
			setSelectedSubBreed({ id: event.target.value, name: event.target.innerText});
		}
	}

	const handleDateChange = (event) => {
		if (event.target.value) {
			props.petInfo.petBirth = event.target.value;
			const year = new Date().getUTCFullYear();
			const birthYear = (event.target.value).getUTCFullYear();
			props.petInfo.petAge = year - birthYear;
		}
 	}
	
	return (
		<Grid container spacing={2}>
			<Grid item xs={12}>
				<TextField
						variant="outlined"
						required
						fullWidth
						id="petname"
						label="Pet Name"
						name="petname"
						autoComplete="petname"
						onChange={(e) => {props.petInfo.petName = e.target.value}}
				/>
			</Grid>
			<MuiPickersUtilsProvider utils={DateFnsUtils}>
      	<Grid container justify="space-around">
					<KeyboardDatePicker
						disableToolbar
						variant="inline"
						format="MM/dd/yyyy"
						margin="normal"
						id="date-picker-inline"
						label="Date picker inline"
						onChange={handleDateChange}
						KeyboardButtonProps={{
							'aria-label': 'change date',
						}}
					/>
				</Grid>
			</MuiPickersUtilsProvider>
			<Grid item xs={12}>
				<TextField
						variant="outlined"
						required
						fullWidth
						id="petage"
						label="Pet Age"
						type="number"
						name="petage"
						autoComplete="petage"
						onChange={(e) => {props.petInfo.petAge = e.target.value}}
				/>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel htmlFor="petBreed" id="petBreed">Pet</InputLabel>
					<Select id="petBreed" 
						value={selectedTopBreed.id} 
						renderValue={() => selectedTopBreed.name}>
					{ topBreed.map((b, i) => {
						return (
							<div key={'TopBreedElemArr'+(i)}>
								<ListSubheader>{!b.breedName? b.find(e => e.breedPid == null).breedName : b.breedName }</ListSubheader>
								{
									(Array.isArray(b))?
									( b.map((e, j) => {
													return (e.breedPid ? <MenuItem onClick={changeBreedId} key={'TopBreedElemArrItem'+(e.breedId*j)} value={e.breedId}>{e.breedName}</MenuItem> : '')
										})
									): <MenuItem onClick={changeBreedId} value={b.breedId}>{b.breedName}</MenuItem>
								}
							</div>
						)
					})}
					</Select>
				</FormControl>
			</Grid>
			<Grid item xs={12}> 
				<FormControl className={classes.formControl} disabled={!subBreed.length}>
					<InputLabel htmlFor="subBreeds" id="subBreeds">Breeds</InputLabel>
					<Select id="subBreeds" value={selectedSubBreed.id} onChange={changeSubBreedId}>
						{
							subBreed.map((b, u) => {
								return (<MenuItem key={'SubBreedElemItem'+(u)} value={b.subBreedId}>{b.breedName}</MenuItem>)
							})
						}
					</Select>
				</FormControl>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel id="gender">Gender</InputLabel>
					<Select labelId="gender" defaultValue="Male" id="select" onChange={(e)=>{props.petInfo.petGender = e.target.value}}>
						<MenuItem value="Male">Male</MenuItem>
						<MenuItem value="Female">Female</MenuItem>
						<MenuItem value="Neuter male">Neuter male</MenuItem>
						<MenuItem value="Neuter female">Neuter female</MenuItem>
					</Select>
				</FormControl>
			</Grid>
		</Grid>
	)
}

export default WithPetForm;