import React, { Component, useEffect, useState, createContext } from 'react'
import Axios from 'axios'
import { makeStyles } from '@material-ui/core/styles'
import { Form, Field, reduxForm } from 'redux-form'
import RenderCheckbox  from '../Component/RenderCheckbox'
import Constants from '../services/constants'
import { TextField, Select, MenuItem, FormControl, Grid, InputLabel } from '@material-ui/core'

const useStyles = makeStyles((theme) => ({
  formControl: {
    minWidth: '100%',
  },
}));

function WithPetForm(props) {
	const classes = useStyles();

	const breed = Axios.get(Constants.Url.pet.breed)
		.then( res => {
			return res.data;
		});
	
	const changeBreedId  = event => {
		console.log(event.target.value);
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
					<Select labelId="petBreed" id="select" onChange={changeBreedId}>
						{
							breed.map((b) => (
								<MenuItem value="b.breedId">{b.breedName}</MenuItem>
							))
						}
					</Select>
				</FormControl>
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel id="petBreed">Pet</InputLabel>
					<Select labelId="petBreed" id="select">
						{
						}
						<MenuItem value="male">male</MenuItem>
						<MenuItem value="female">female</MenuItem>
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