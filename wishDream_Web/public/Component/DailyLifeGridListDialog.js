import React, { Component, Fragment, useEffect } from 'react'
import Typography from '@material-ui/core/Typography'
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import TextField from '@material-ui/core/TextField';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import PersonIcon from '@material-ui/icons/Person';
import Avatar from '@material-ui/core/Avatar';
import CloseIcon from '@material-ui/icons/Close';
import { withStyles, makeStyles } from '@material-ui/styles'

const styles = theme => ({
    root: {
      margin: 0,
      padding: theme.spacing(2),
    },
    closeButton: {
      position: 'absolute',
      right: theme.spacing(1),
      top: theme.spacing(1),
      color: theme.palette.grey[500],
    }
  });

const useStyles = makeStyles({
    titleBox: {
        margin: 0,
        padding: '15px 15px 10px 15px',
        lineHeight: '0.3em',
    },
    avatarBox: {
        display: 'flex',
        '& > *': {
            marginLeft: '10px',
        },
        justifyContent: 'flex-end',
        padding: '10px 20px 10px 0'
        
    },
    author: {
        marginTop: '0.35em'
    }
});

const DialogTitle = withStyles(styles)(props => {
    const { children, classes, onClose, ...other } = props;
    return (
        <MuiDialogTitle disableTypography className={classes.root} {...other}>
        {onClose ? (
            <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
            <CloseIcon />
            </IconButton>
        ) : null}
        </MuiDialogTitle>
    );
});

const DialogContent = withStyles(theme => ({
    root: {
        padding: theme.spacing(2),
    },
}))(MuiDialogContent);

const DialogActions = withStyles(theme => ({
    root: {
        margin: 0,
        padding: theme.spacing(1),
    },
}))(MuiDialogActions);

const TypographyTitle = withStyles(theme => ({
    root: {
        color: theme.palette.grey[600],
        width: '93%'
    }
}))(Typography);

const DailyLifeGridListDialog = React.forwardRef(({open, close, title, author, no}, ref) =>  {
    const classes = useStyles();
    const defaultImg = '';

    if (!no) {
        close();
    }

    return (
        <Dialog onClose={close} aria-labelledby="customized-dialog-title" open={open} ref={ref}>
            <DialogTitle id="customized-dialog-title" onClose={close}>
            </DialogTitle>
            <div className={classes.titleBox}>
                <TypographyTitle variant="h6">{title}</TypographyTitle>
            </div>
            <div className={classes.avatarBox}>
                <Avatar><PersonIcon /></Avatar>
                <Typography gutterBottom className={classes.author}>
                    {author}
                </Typography>
            </div>
            <DialogContent dividers>
            <Typography gutterBottom>
                Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis
                lacus vel augue laoreet rutrum faucibus dolor auctor.
            </Typography>
            <Typography gutterBottom>
                Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel
                scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus
                auctor fringilla.
            </Typography>
            </DialogContent>
            <DialogActions>
            <TextField id="outlined-basic" label="Outlined" variant="outlined" />
            <Button autoFocus onClick={close} color="primary">
                Comment
            </Button>
            </DialogActions>
            <DialogContent dividers>
            <Typography gutterBottom>
                Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
                in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
            </Typography>
            </DialogContent>
        </Dialog>
    )
});

export default DailyLifeGridListDialog