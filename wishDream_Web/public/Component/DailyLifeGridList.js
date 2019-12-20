import React, { Component, Fragment } from 'react'
import Typography from '@material-ui/core/Typography'
import { makeStyles, useTheme } from "@material-ui/styles";
import { Grid, GridList, IconButton, GridListTile, ListSubheader, Paper } from '@material-ui/core';
import StarBorderRoundedIcon from '@material-ui/icons/StarBorderRounded';
import Axios from 'axios';
import Constants from '../services/constants'

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
        overflow: 'hidden'
    },
    paper: {
        height: 260,
        width: 240,
    },
    control: {
        // padding: theme.spacing(1),
    },
}));

const DailyLifeGridList = () => {
    const [spacing, setSpacing] = React.useState(1);
    const classes = useStyles();
    const theme = useTheme();
    let tileData = [];
    Axios.get(Constants.Url.tmp.gridListData)
    .then(function (res) {
        // TODO: 데이터 비었을 때, 보일 수 있게 틀 만들기
        // TODO: 데이터 보이게 하기
        // tileData = JSON.parse(res.data); 
        console.log(res);
    }).catch(function (error) {
        console.log(error);
    })

    return (
        <Grid container className={classes.root} spacing={1}>
            <Grid item xs={6} sm={3}>
                <Grid container justify="center" spacing={spacing}>
                    <GridList cellHeight={210} className={classes.gridList}>
                        <GridListTile key="Subheader" cols={5} style={{height: 'auto'}}>
                            <ListSubheader component="div">December</ListSubheader>
                        </GridListTile>
                        {tileData.map(tile => (
                            <Paper key={tile.img} className={classes.paper}>
                                <GridListTile>
                                    <img src={tile.img} alt={tile.title} />
                                    <GridListTileBar
                                        title={tile.title}
                                        subtitle={<span>by: {tile.author}</span>}
                                        actionIcon={
                                            <IconButton aria-label={`info about ${tile.title}`} className={classes.icon}>
                                            <StarBorderRoundedIcon />
                                            </IconButton>
                                        }
                                    />
                                </GridListTile>
                            </Paper>
                        ))}
                    </GridList>
                </Grid>
            </Grid>
        </Grid>
    )
};

export default DailyLifeGridList;