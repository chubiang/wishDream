import React, { Component, Fragment, useEffect } from 'react'
import Typography from '@material-ui/core/Typography'
import { makeStyles, useTheme } from "@material-ui/styles";
import { Grid, GridList, IconButton, GridListTile, GridListTileBar, ListSubheader } from '@material-ui/core';
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
    gridList: {
        padding: '2px'
    },
    gridTile: {
        cursor: 'pointer',
        '&:after': {
            content: '',

        },
        '&:hover': {
            border: '1px solid rgba(0, 173, 255, 1)',
            filter: 'opacity(0.8)'
        }
    },
}));

const DailyLifeGridList = (props) => {
    const [spacing, setSpacing] = React.useState(1);
    const classes = useStyles();
    const theme = useTheme();
    const [tileData, setTailData] = React.useState([]);
    let gridListTitle = "Pet boast";
    useEffect(() => {
        if (!tileData.length) {
            getGridData();
        }
    });

    function getGridData() {
        Axios.get(Constants.Url.tmp.gridListData)
        .then(function (res) {
            // TODO: 데이터 비었을 때, 보일 수 있게 틀 만들기
            // TODO: 데이터 보이게 하기
            setTailData(res.data);
        }).catch(function (error) {
            console.log(error);
        });
    }

    function tileClick() {
        console.log('click!');
        
    }

    return (
        <Grid container className={classes.root}>
            <Grid item>
                <Grid container justify="center" spacing={spacing}>
                    <GridList cellHeight={210} className={classes.gridList}>
                        <GridListTile key="Subheader" cols={5} style={{height: 'auto'}}>
                            <ListSubheader component="div">{gridListTitle}</ListSubheader>
                        </GridListTile>
                        {tileData.map((tile, index) => (
                            <GridListTile key={index} onClick={()=> {tileClick()}} className={classes.gridTile}>
                                <img src={tile.img} alt={tile.title} />
                                <GridListTileBar
                                    title={tile.title}
                                    subtitle={<span>by: {tile.author}</span>}
                                    actionIcon={
                                        <IconButton aria-label={`info about ${tile.title}`} className={classes.icon}>
                                        <StarBorderRoundedIcon style={{color: 'rgba(255, 255, 255, 0.80)'}} />
                                        </IconButton>
                                    }
                                />
                            </GridListTile>
                        ))}
                    </GridList>
                </Grid>
            </Grid>
        </Grid>
    )
};

export default DailyLifeGridList;