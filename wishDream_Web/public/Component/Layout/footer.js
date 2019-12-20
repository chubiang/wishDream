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
        position: 'absolute',
        bottom: 0,
        marginBottom: 20
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