// module "footer.js"
import React, { Component, Fragment } from 'react'
import Typography from '@material-ui/core/Typography'
import Link from '@material-ui/core/Link'
import Box from '@material-ui/core/Box'
import { Copyright } from 'Component/Copyright'
import { makeStyles } from '@material-ui/core/styles'

const useStyles = makeStyles({
    footer: {
        display: 'flex',
        justifyContent: 'center'
    },
    footerDIV: {
        position: 'relative',
        margin: 0,
        padding: '50px'
    }
});

export default function Footer() {
    const classes = useStyles();
    return (
        <footer className={classes.footer}>
            <Box mt={8} className={classes.footerDIV}>
                <Copyright />
            </Box>
        </footer>
    )
}