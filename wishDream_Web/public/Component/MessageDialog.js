import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { AlertContext } from '../Page/SignInForm';

const MessageDialog = ({fullWidth, maxWidth, title, content, handleClose, buttons}) => {

    return (
        <AlertContext.Consumer>
            { open =>
            (<Dialog
                fullWidth={fullWidth}
                maxWidth={maxWidth}
                open={open}
                onClose={handleClose}
                aria-labelledby="max-width-dialog-title">
                <DialogTitle id="max-width-dialog-title">{title}</DialogTitle>
                <DialogContent>
                <DialogContentText>
                    {content}
                </DialogContentText>
                </DialogContent>
                <DialogActions>
                    {buttons.map((button, i) => {
                        return (<MessageButton 
                                    key={i}
                                    classes={button.classes}
                                    text={button.text}
                                    handleClose={handleClose}
                                    color={button.color} />)
                    })}
                </DialogActions>
            </Dialog>)
            }
        </AlertContext.Consumer>
    )
}

const MessageButton = ({handleClose, classes, text, color}) => {
    return (
        <Button className={classes} onClick={handleClose} color={color}>
            {text}
        </Button>
    )
}

export default MessageDialog;