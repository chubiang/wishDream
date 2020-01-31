import React from 'react'
import { withStyles } from '@material-ui/core/styles'
import Menu from '@material-ui/core/Menu'
import MenuItem from '@material-ui/core/MenuItem'
import ListItemIcon from '@material-ui/core/ListItemIcon'
import ListItemText from '@material-ui/core/ListItemText'

import { grey } from '@material-ui/core/colors'
import { IconButton } from '@material-ui/core'
import NotificationsIcon from '@material-ui/icons/Notifications'

import NoticeType from '../services/noticeType'

const StyledMenu = withStyles({
  paper: {
    border: '1px solid #d3d4d5',
  },
})(props => (
  <Menu
    elevation={0}
    getContentAnchorEl={null}
    anchorOrigin={{
      vertical: 'bottom',
      horizontal: 'center',
    }}
    transformOrigin={{
      vertical: 'top',
      horizontal: 'center',
    }}
    {...props}
  />
))

const StyledMenuItem = withStyles(theme => ({
  root: {
    '&:focus': {
      backgroundColor: theme.palette.primary.main,
      '& .MuiListItemIcon-root, & .MuiListItemText-primary': {
        color: theme.palette.common.white,
      },
    },
  },
}))(MenuItem)

export function useCurrentMenu(target) {
  return target
}

export default function CustomizedMenus(props) {
  const [anchorEl, setAnchorEl] = React.useState(null)

  function handleMenuClick(event) {
    setAnchorEl(event.currentTarget)
  }

  function handleClose() {
    setAnchorEl(null)
  }

  const iconRef = [];
  const menuItems = props.menus.map((obj, index) => {
    // iconRef.push({index:React.useRef()});
    return ( 
      <StyledMenuItem key={'alarmItem' + index}>
        <ListItemIcon>
          {/* <NoticeType type={obj.noticeType} ref={iconRef[index][index]} /> */}
        </ListItemIcon>
        <ListItemText primary={obj.title} />
      </StyledMenuItem>
    )
  })

  return (
    <>
      <IconButton
        aria-controls="customized-menu"
        aria-haspopup="true"
        variant="contained"
        onClick={handleMenuClick}
      >
        {props.menuIcon}
      </IconButton>
      <StyledMenu
        id="customized-menu"
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleClose}
      >
        {menuItems}
      </StyledMenu>
    </>
    
  )
}
