import React, { useState, useEffect, useContext, useReducer } from 'react'
import clsx from 'clsx'

export default function CustomizedFormControlLabel(props) {
    
    return (
        <div className="customformContol">
            {props.control}
            <label for={props.controlId}>
                {props.label}
            </label>
        </div>
    )
}