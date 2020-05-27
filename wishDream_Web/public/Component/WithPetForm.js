import React, { Component, useEffect, useRef, useState, createContext, Fragment } from 'react'
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
	props = { selected: { topBreed: '', subBreed: '' } };
	const [state, setState] =  React.useState(props.selected);
	const selectRef = useRef();
	
	useEffect(() => {
		if (!topBreed.length) {
			Axios.get(Constants.Url.pet.breed)
			.then( res => {
					setTopBreed(res.data);
			});
		}
	})
	
	const changeBreedId  = (event) => {
		props.selected.topBreed = event.target.value;
		
		if (props.selected.topBreed) {
			Axios.get(Constants.Url.pet.breed + "/" + props.selected.topBreed)
			.then( res => {
				console.log('res',res);
				setSubBreed(res.data);
			});
			setState(props.selected.topBreed);
			console.log('select ',props.selected.topBreed, state);
		}
	}
	
	const SubBreedSelect = (props) => {
		return (
			<FormControl className={classes.formControl}>
				<InputLabel id="breeds">Breeds</InputLabel>
				<Select labelId="breeds" id="breeds">
					{
						subBreed.map((b, u) => 
							<MenuItem key={'SubBreedElemItem'+(u)} value={b.breedId}>{b.breedName}</MenuItem>
						)
					}
				</Select>
			</FormControl>
		)
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
				{topBreed.length ?
					(<FormControl className={classes.formControl}>
						<InputLabel id="petBreed">Pet</InputLabel>
						<Select labelId="petBreed" id="petBreed" value={props.selected.topBreed}>
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
					</FormControl>) : ''
				}
			</Grid>
			<Grid item xs={12}>
				{/* {subBreed.length? <SubBreedSelect />: ''} */}
			</Grid>
			<Grid item xs={12}>
				<FormControl className={classes.formControl}>
					<InputLabel id="gender">Gender</InputLabel>
					<Select labelId="gender" defaultValue="male" id="select">
						<MenuItem value="male">male</MenuItem>
						<MenuItem value="female">female</MenuItem>
					</Select>
				</FormControl>
			</Grid>
		</Grid>
	)
}

export default WithPetForm;