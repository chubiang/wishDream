import React, { useState, useEffect, useContext, useReducer } from 'react'
import { FormControlLabel, FormControl, Checkbox } from '@material-ui/core'
import { withStyles } from '@material-ui/core/styles'
import { pink } from '@material-ui/core/colors';


const RenderCheckbox = ({name, value, label, change}) => {
    return (
        <div>
           <Checkbox
                color="secondary"
                onChange={change}
                checked={value}
                name={name}
                />
            <span>{label}</span>
        </div>
    )
}

export default RenderCheckbox