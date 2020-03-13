import React, { Component, Fragment, useEffect } from 'react'
import Typography from '@material-ui/core/Typography'
import { makeStyles, useTheme } from '@material-ui/styles'
import { Grid, GridList, IconButton, GridListTile, GridListTileBar, ListSubheader } from '@material-ui/core'
import StarBorderRoundedIcon from '@material-ui/icons/StarBorderRounded'
import Axios from 'axios'

import Constants from '../services/constants'
import LoadingBar from './LoadingBar'
import DailyLifeGridListDialog  from './DailyLifeGridListDialog'
import { DailyListGridMockList } from '../MockData/DailyLifeGridMock'

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-around',
        overflow: 'hidden'
    },
    gridList: {
        padding: '2px',
        width: '100%',
        [theme.breakpoints.down('sm')]: {
            overflowX: 'auto',
            width: '80%'
        }
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
            filter: 'opacity(0.8)'
        }
    },
}))

const DailyLifeGridList = (props) => {
    const [spacing, setSpacing] = React.useState(2)
    const classes = useStyles()
    const theme = useTheme()
    const [tileData, setTailData] = React.useState([])
    const [loadingBar, setLoadingBar] = React.useState(false)
    const [open, setOpen] = React.useState(false)
    const openRef = React.createRef()
    const [gridCont, setGridCont] = React.useState({})
    const [popDialog, setPopDialog]= 

    let gridListTitle = 'Show Off My Pet'
        
    useEffect(() => {
        if (!tileData.length) {
            setTailData(DailyListGridMockList)
            getGridData()
        }
    })

    function getGridData() {
        setLoadingBar(true)
        setTimeout(() => {
        Axios.get(Constants.Url.tmp.gridListData)
        .then(function (res) {
            setTailData(res.data)
        }).catch(function (error) {
            console.log(error)
        }).finally(function() {
            setLoadingBar(false)
        })
        }, 10000);
    }

    const handleOpen = (e) => {
        setOpen(true)
        console.log(e.currentTarget)
        console.log(e.currentTarget.innerText)
        let reg =  /[\w+\s]+[\s+\w]/g;
        let txtArr = e.currentTarget.innerText.match(reg)
        if (txtArr && txtArr.length) {
            txtArr[0] = txtArr[0].replace(/(\s)by/, '')
            setGridCont({
                title: txtArr[0],
                authorName: txtArr[1]
            });
        }
    }
    // 즐겨찾기 추가
    const forkGridTile = (e) => {
        console.log(e.currentTarget)
        setOpen(false)
        
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
                        <GridList cellHeight={340} className={classes.gridList} cols={2}>
                            <GridListTile key='Subheader' cols={2} style={{height: 'auto'}}>
                                <ListSubheader component='div' className={classes.gridListTitle}>{gridListTitle}</ListSubheader>
                            </GridListTile>
                            {tileData.map((tile, index) => (
                                <GridListTile key={index} onClick={handleOpen} className={classes.gridTile}>
                                    <img src={tile.image.img} alt={tile.title} />
                                    <GridListTileBar
                                        title={tile.title}
                                        subtitle={<span>by: {tile.author.username}</span>}
                                        actionIcon={
                                            <IconButton aria-label={`info about ${tile.title}`} className={classes.icon} onClick={forkGridTile}>
                                            <StarBorderRoundedIcon  style={{color: 'rgba(255, 255, 255, 0.80)'}} />
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
                title={gridCont.title} author={gridCont.authorName}
                no={gridCont.no} 
                ref={openRef}/>
        </>
    )
}

export default DailyLifeGridList