import React from 'react';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Popover from '@material-ui/core/Popover';
import Axios from 'axios';
import Constants from '../services/constants';
import PopupState, { bindTrigger, bindPopover } from 'material-ui-popup-state';
import { Card, CardActionArea, CardMedia, CardContent, CardActions } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';


const useStyles = makeStyles({
    userPop: {
        width: 320,
        maxWidth: 320,
        minWidth: 320,
        minHeigth: 350,
    },
    card: {
        width: 320,
        maxWidth: 320,
        minWidth: 320,
        minHeigth: 350,
    },
    userImg: {
        height: 180,
        minHeigth: 180,
        maxheight: 180,
        margin: 16,
        borderRadius: 8,
    },
    cardBtn: {
        justifyContent: 'space-around'
    }
});

export default function PopUserInfo(props) {
    const classes = useStyles();
    const comment = [
        'Have a good day!'
    ];

    function subContent(subContent) {
        return (
            subContent.map((v, i) => (
                <Typography key={i} variant="body2" color="textSecondary" component="p">
                    {v}
                </Typography>
            ))
        )
    }

    const logOut = () => {
        const config = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        };
        Axios.post(Constants.Url.member.logout, null, config)
            .then((res) => {
                location.reload();
            });
    }

    return (
        <PopupState variant="popover" popupId="popUserInfo" className={classes.userPop}>
            {popupState => (
                <div>
                <Button variant="contained" color="primary" {...bindTrigger(popupState)}>
                    {props.username}
                </Button>
                <Popover
                    {...bindPopover(popupState)}
                    anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'center',
                    }}
                    transformOrigin={{
                    vertical: 'top',
                    horizontal: 'center',
                    }}
                >
                    <Card className={classes.card}>
                        <CardActionArea>
                            <CardMedia 
                                className={classes.userImg}
                                image="/images/myAngel.jpg"
                                title="Contemplative Reptile" />
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="h2">
                                    Hello! {props.username}
                                </Typography>
                                {subContent(comment)}
                            </CardContent>
                        </CardActionArea>
                        <CardActions className={classes.cardBtn}>
                            <Button size="small" color="primary">
                                My History
                            </Button>
                            <Button size="small" color="primary">
                                My Pet
                            </Button>
                        </CardActions>
                        <CardActions className={classes.cardBtn}>
                            <Button onClick={logOut} size="small" color="primary">
                                LogOut
                            </Button>
                        </CardActions>
                    </Card>
                </Popover>
                </div>
            )}
        </PopupState>
    )
}