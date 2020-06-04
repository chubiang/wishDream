import 'date-fns';
import React, { Component, useEffect, useState, Fragment } from 'react'
import Axios from 'axios'
import { MuiThemeProvider, makeStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import { Form, Field, reduxForm } from 'redux-form'
import RenderCheckbox  from '../Component/RenderCheckbox'
import Constants from '../services/constants'
import { TextField, Select, ListSubheader, MenuItem, FormControl, Grid, InputLabel } from '@material-ui/core'
import DateFnsUtils from '@date-io/date-fns';
import { GlobalTheme } from '../Page/LoginPage'
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
} from '@material-ui/pickers';
import "core-js/modules/es.promise"

const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: '100%',
  },
}));

function WithPetForm(props) {
	const classes = useStyles();

	const [topBreed, setTopBreed] = useState([]);
	const [subBreed, setSubBreed] = useState([]);
	const [selectedTopBreed, setSelectedTopBreed] = useState({ id: '', name: '' });
	const [selectedSubBreed, setSelectedSubBreed] = useState({ id: '', name: '' });
	const [petGender, setPetGender] = useState("Male");
	const [petBirth, setPetBirth] = useState({}); 
	const [hasError, setHasError] = useState(false);

	useEffect(() => {
		
		if (!topBreed.length) {
			Axios.get(Constants.Url.pet.breed)
			.then( res => {
					setTopBreed(res.data);
			});
		}
		if (!selectedSubBreed.id.length) {
			setHasError(true);
		}
		if (props.petInfo) {
			props.petInfo.petBreedId = selectedSubBreed.id;
			props.petInfo.petGender = petGender;
			if (petBirth) {
				props.petInfo.petBirth = petBirth.birthday;
				props.petInfo.petAge = petBirth.age;
			}
		}
	})
	
	const changeBreedId = async (event) => {
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
	const changePetGender = (event) => {
		if (event.target.value) {
			setPetGender(event.target.value);
			props.petInfo.petGender = petGender;
		}
	}

	const handleAgeChange = (event) => {
		const date = new Date();
		const setDate = (date.getFullYear() - event.target.value)
						+ '-' + (date.getMonth() + 1)
						+ '-' + date.getDay();
		setPetBirth({age: event.target.value, birthday: new Date(setDate)});
	}

	const handleDateChange = (date) => {
		if (date) {
			const year = new Date().getFullYear();
			const birthYear = (date).getFullYear();
			setPetBirth({age: year - birthYear, birthday: date});
		}
	 }
	
	return (
		<Grid container spacing={2}>
			<Grid item xs={12}>
				<TextField
						variant="outlined"
						required
						fullWidth
						id="petName"
						label="Pet Name"
						name="petname"
						onChange={(e) => {props.petInfo.petName = e.target.value}}
				/>
			</Grid>
			<Grid item xs={12}>
				<MuiPickersUtilsProvider utils={DateFnsUtils}>
					<MuiThemeProvider theme={GlobalTheme}>
						<KeyboardDatePicker
							variant="outlined"
							format="MM/dd/yyyy"
							margin="normal"
							id="petBirthday"
							label="Pet birthday"
							onChange={handleDateChange}
							value={petBirth.birthday}
							KeyboardButtonProps={{
								'aria-label': 'change date',
							}}
							/>
					</MuiThemeProvider>
				</MuiPickersUtilsProvider>
			</Grid>
			<Grid item xs={12}>
				<TextField
						variant="outlined"
						required
						fullWidth
						id="petAge"
						label="Pet Age"
						type="number"
						name="petage"
						value={petBirth.age}
						onChange={handleAgeChange}
				/>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl} error={hasError}>
					<InputLabel htmlFor="petBreed" id="petBreed">Pet</InputLabel>
					<Select id="petBreed" required
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
					{hasError && <FormHelperText>This is required!</FormHelperText>}
				</FormControl>
			</Grid>
			<Grid item xs={12}> 
				<FormControl className={classes.formControl} disabled={!subBreed.length} error={hasError}>
					<InputLabel htmlFor="subBreeds" id="subBreeds">Breeds</InputLabel>
					<Select id="subBreeds" value={selectedSubBreed.id} onChange={changeSubBreedId} required>
						{
							subBreed.map((b, u) => {
								return (<MenuItem key={'SubBreedElemItem'+(u)} value={b.subBreedId}>{b.breedName}</MenuItem>)
							})
						}
					</Select>
					{hasError && <FormHelperText>This is required!</FormHelperText>}
				</FormControl>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel id="gender">Gender</InputLabel>
					<Select name="petGender" labelId="gender" value={petGender}
						id="select" onChange={changePetGender}>
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