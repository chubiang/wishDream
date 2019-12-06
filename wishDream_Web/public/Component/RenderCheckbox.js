import React, { useState, useEffect, useContext, useReducer } from 'react'
import clsx from 'clsx'
import { FormControlLabel, FormControl, Checkbox } from '@material-ui/core'
import { withStyles } from '@material-ui/core/styles'
import { pink } from '@material-ui/core/colors';


const RenderCheckbox = ({value, label, change}) => {
    useEffect(() => {
        console.log(value, label, change);
        
    });

    return (
        <div>
           <Checkbox
                color="secondary"
                onChange={change}
                />
            <span>{label}</span>
        </div>
    )
}

export default RenderCheckbox