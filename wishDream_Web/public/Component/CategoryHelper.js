import React, { Component, Fragment, useEffect } from 'react'
import Typography from '@material-ui/core/Typography'
import { makeStyles, useTheme } from '@material-ui/styles'


const Helper = ({menu}) => menu.map((obj, index) => {
    return (
        <p>
            <span>menu</span>
            <span>></span>
        </p>
    )
})

const CategoryHelper = (props) => {

    

    return (
        <div>
            <Helper menus={props.menu} />
        </div>
    )
}

export default CategoryHelper