import React, { useState, useEffect, useContext, useReducer } from 'react'
import clsx from 'clsx'
import { FormControlLabel, FormControl, Checkbox } from '@material-ui/core'
import { withStyles } from '@material-ui/core/styles'
import { pink } from '@material-ui/core/colors';


const RendorCheckbox = ({value, label}) => {

    return (
        <div>
           <Checkbox
                checked={value}
                color="secondary"
                />
            <span>{label}</span>
        </div>
    )
}

export default RendorCheckbox