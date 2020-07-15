// module "header.js"
import React from 'react'
import SockJsClient from 'react-stomp'
import clsx from 'clsx'
import axios from 'axios'

import { BrowserRouter, Redirect, Link, Route } from 'react-router-dom'

import { makeStyles, withStyles, useTheme } from '@material-ui/core/styles'

import Drawer from '@material-ui/core/Drawer'
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import Divider from '@material-ui/core/Divider'
import Typography from '@material-ui/core/Typography'
import MenuItem from '@material-ui/core/MenuItem'
import MenuIcon from '@material-ui/icons/Menu'
import MailIcon from '@material-ui/icons/Mail'
import IconButton from '@material-ui/core/IconButton'
import InboxIcon from '@material-ui/icons/MoveToInbox'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import ChevronRightIcon from '@material-ui/icons/ChevronRight'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import ListItemIcon from '@material-ui/core/ListItemIcon'
import ListItemText from '@material-ui/core/ListItemText'
import { grey } from '@material-ui/core/colors';
import NotificationsIcon from '@material-ui/icons/Notifications';
import Badge from '@material-ui/core/Badge';

import constants from '../../services/constants'
import CustomizedMenus from 'Component/CustomizedMenus'
import PopUserInfo from 'Component/PopUserInfo'
import Home from 'Page/Home'
import About from 'Page/About'
import FindMember from 'Page/FindMember'
import RepeatListItem from '../RepeatListItem'
import Axios from 'axios'

const drawerWidth = 240
const appTitle = 'WishDream';

const headerStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    backgroundColor: theme.palette.primary,
    color: theme.palette.whiteFont,
    iconColor: theme.palette.whiteIcon
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  hide: {
    display: 'none',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
  },
  drawerOpen: {
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerClose: {
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: 'hidden',
    width: theme.spacing(7) + 1,
    [theme.breakpoints.down('sm')]: {
      width: 0,
    }
  },
  toolbar: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    ...theme.mixins.toolbar,
  },
  title: {
     flexGrow: 1,
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
  },
}))
const NotifyIcon = ({count}) => { 
  return (
    <Badge badgeContent={count} color="secondary">
      <NotificationsIcon style={{color: grey[100]}}/>
    </Badge>
  ) 
};

const url = "ws://localhost:8080/topic/alarm";
let ws = null;

export default function Header (props) {
  const classes = headerStyles()
  const theme = useTheme()
  const [alarmList, setAlarmList] = React.useState([])
  const [open, setOpen] = React.useState(false)
  const [connected, setConnected] = React.useState(false);
  const [username, setUsername] = React.useState('')
  const [menus, setMenus] = React.useState([]);
  


  React.useEffect(() => {
    // effect
    //getAlarmList();
    if (!username.length) { //oauth2로 로그인 시에
      Axios.get(constants.Url.member.username)
      .then((res) => {
        console.log('res', res);
        setUsername(res.data);
      });
    }
    if (!menus.length) {
      Axios.get(constants.Url.member.menu)
      .then((res) => {
        console.log('res', res);
      });
    }
    if (!ws) {
      setConnected(onConnect());
    }
  }, [alarmList])

  function onConnect() {
    ws = new WebSocket(url);
    ws.onopen = function(e) {
      console.log('Info: Connection Established.')
    }
    ws.onerror = function(ev) {
      console.log('Info: Connection Error.')
    };
    ws.onmessage = function(event) {
      console.log('Info: received Message = '+ event.data)
      setAlarmList(JSON.parse(event.data))
    };
  
    ws.onclose = function(event) {
      console.log('Info: Closing Connection.')
        return false;
    };
    return true;
  }
  
   function send() {
      if (!!ws) {
        ws.send('alarm');
      }
   }
  
  function disconnect() 
  {
      if (ws != null) {
          ws.close();
          ws = null;
      }
      setConnected(false);
  }
  

  function getAlarmList() {
    axios.get(constants.Url.socket.alarmList)
    .then(function(res) {
      console.log(res);
      setAlarmList(res.data);
    }).catch(function (error) {
        console.log(error);
    });
  }

  function handleDrawerOpen() {
    setOpen(true)
  }

  function handleDrawerClose() {
    setOpen(false)
  }

  
  function alarmButton(list) {
    return (
        <CustomizedMenus menus={list} menuIcon={<NotifyIcon count={list.length} type={list.noticeType}/>} />
    );
  }
  function popOverUserInfo() {
    return <PopUserInfo username={username} />
  }

  return (
    <div className={classes.root}>
      {/* <SockJsClient url='http://localhost:8080/topic' topics={['/alarm']}
          onMessage={(msg) => { console.log(msg); }}
          onConnect={() => { setConnected(true) }}
          onDisconnect={() => { setConnected(false) }}
          debug={true} /> */}
      <AppBar
        position="fixed"
        className={clsx(classes.appBar, {
          [classes.appBarShift]: open,
        })}
      >
        <Toolbar>
          <IconButton
            edge="start"
            className={clsx(classes.menuButton, {
              [classes.hide]: open,
            })}
            color={classes.root.iconColor}
            aria-label="open drawer"
            onClick={handleDrawerOpen}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title} noWrap>
          <Link to="/">{appTitle}</Link>
          </Typography>
          {alarmButton(alarmList)}
          {popOverUserInfo()}
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        className={clsx(classes.drawer, {
          [classes.drawerOpen]: open,
          [classes.drawerClose]: !open,
        })}
        classes={{
          paper: clsx({
            [classes.drawerOpen]: open,
            [classes.drawerClose]: !open,
          }),
        }}
        open={open}
      >
        <div className={classes.toolbar}>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </div>
        <Divider />
        {menus.map((items, index) => (
          <React.Fragment key={'menus'+index}>
            <List>
              {RepeatListItem({items: items})}
            </List>
            <Divider />
          </React.Fragment>
        ))}
      </Drawer>
    </div>
  )
}
