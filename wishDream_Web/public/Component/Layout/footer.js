// module "footer.js"
import React, { Component, Fragment } from 'react'
import Typography from '@material-ui/core/Typography'
import Link from '@material-ui/core/Link'
import Box from '@material-ui/core/Box'
import { Copyright } from 'Component/Copyright'

export default function Footer() {

    return (
        <footer className="footer">
            <Box mt={8}>
                <Copyright />
            </Box>
        </footer>
    )
}