import React, { Component, useEffect, useState, createContext } from 'react'
import Axios from 'axios'
import { makeStyles } from '@material-ui/core/styles'
import { Form, Field, reduxForm } from 'redux-form'
import RenderCheckbox  from '../Component/RenderCheckbox'
import Constants from '../services/constants'
import { TextField, Select, ListSubheader, MenuItem, FormControl, Grid, InputLabel } from '@material-ui/core'

const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: '100%',
  },
}));

function WithPetForm(props) {
	const classes = useStyles();

	const [topBreed, setTopBreed] = React.useState([]);
	const [subBreed, setSubBreed] = React.useState([]);
	props = { selected: {} };
	const [state, setState] =  React.useState(props.selected);
	
	useEffect(() => {
		if (!topBreed.length) {
			Axios.get(Constants.Url.pet.breed)
			.then( res => {
					console.log('res',res);
					setTopBreed(res.data);
			});
		}
	})
	
	const optionElem = function(array) {
		return (
			array.map((e, i) => {
				if (e.breedPid == null) array.splice(i, 1)
				else return (<MenuItem key={'Sub'+i} value={e.breedId}>{e.breedName}</MenuItem>)
			})
		)
	}

	const topBreedElem = function() {
		topBreed.map((b, i) => {
			if (Array.isArray(b)) {
				const category = b.find(e => e.breedPid == null);
				
				return (
					<ListSubheader key={'Top'+i} label={category.breedName}>
						{optionElem(b)}
					</ListSubheader>
				)
			} else {
				return (<MenuItem key={i} value={b.breedId}>{b.breedName}</MenuItem>)
			}
		})
	};

	const subBreedElem = subBreed.map((b, i) => (
		<MenuItem key={i} value={b.breedId}>{b.breedName}</MenuItem>
	));
	
	const changeBreedId  = event => {
		props.selected.topBreed = event.target.value;
		Axios.get(Constants.Url.pet.breed + "/" + props.selected.topBreed)
			.then( res => {
					console.log('res',res);
					setSubBreed(res.data);
			});
		console.log(props.selected);
		setState(props.selected);
		
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
				/>
			</Grid>
			<Grid item xs={12}>
				<TextField
						variant="outlined"
						required
						fullWidth
						id="petage"
						label="Pet Age"
						name="petage"
						autoComplete="petage"
				/>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel id="petBreed">Pet</InputLabel>
					<Select labelId="petBreed" id="petBreed" onChange={changeBreedId}>
						{topBreedElem()}
					</Select>
				</FormControl>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}
					style={!subBreed ? {pointerEvents: "none", opacity: "0.4"} : {}}>
					<InputLabel id="breeds">Breeds</InputLabel>
					<Select labelId="breeds" id="breeds" >
						{subBreed? subBreedElem: ''}
					</Select>
				</FormControl>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel id="gender">Gender</InputLabel>
					<Select labelId="gender" id="select">
						<MenuItem value="male">male</MenuItem>
						<MenuItem value="female">female</MenuItem>
					</Select>
				</FormControl>
			</Grid>
		</Grid>
	)
}

export default WithPetForm;