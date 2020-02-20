import React, { Component, Fragment, useEffect } from 'react'
import Typography from '@material-ui/core/Typography'
import { makeStyles, useTheme } from '@material-ui/styles'
import { Grid, GridList, IconButton, GridListTile, GridListTileBar, ListSubheader } from '@material-ui/core'
import StarBorderRoundedIcon from '@material-ui/icons/StarBorderRounded'
import Axios from 'axios'

import Constants from '../services/constants'
import LoadingBar from './LoadingBar'
import DailyLifeGridListDialog  from './DailyLifeGridListDialog'

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
    gridListTitle: {
        fontSize: '40px',
        fontWeight: 'bolder',
        padding: '30px 0 80px'
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
}))

const DailyLifeGridList = (props) => {
    const [spacing, setSpacing] = React.useState(1)
    const classes = useStyles()
    const theme = useTheme()
    const [tileData, setTailData] = React.useState([])
    const [loadingBar, setLoadingBar] = React.useState(false)
    const [open, setOpen] = React.useState(false)
    const openRef = React.createRef()
    const [gridCont, setGridCont] = React.useState({});

    let gridListTitle = 'Pet boast'
    useEffect(() => {
        if (!tileData.length) {
            getGridData()
        }
        console.log('open',open);
        
    })

    function getGridData() {
        setLoadingBar(true)
        Axios.get(Constants.Url.tmp.gridListData)
        .then(function (res) {
            setTailData(res.data)
        }).catch(function (error) {
            console.log(error)
        }).finally(function() {
            setLoadingBar(false)
        })
    }

    const handleOpen = (e) => {
        setOpen(true)
        console.log(e.currentTarget);
        console.log(e.currentTarget.innerText);
        let reg =  /[\w+\s]+[\s+\w]/g;
        let txtArr = e.currentTarget.innerText.match(reg);
        if (txtArr && txtArr.length) {
            txtArr[0] = txtArr[0].replace(/(\s)by/, '');
            setGridCont({
                title: txtArr[0],
                author: txtArr[1]
            });
        }
    }

    const handleClose = () => {
        setOpen(false)
    };

    return (
        <>
            <LoadingBar loading={loadingBar} />
            <Grid container className={classes.root}>
                <Grid item>
                    <Grid container justify='center' spacing={spacing}>
                        <GridList cellHeight={210} className={classes.gridList}>
                            <GridListTile key='Subheader' cols={5} style={{height: 'auto'}}>
                                <ListSubheader component='div' className={classes.gridListTitle}>{gridListTitle}</ListSubheader>
                            </GridListTile>
                            {tileData.map((tile, index) => (
                                <GridListTile key={index} onClick={handleOpen} className={classes.gridTile}>
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
            <DailyLifeGridListDialog open={open} close={handleClose} 
                title={title} author={author}
                ref={openRef}/>
        </>
    )
}

export default DailyLifeGridList