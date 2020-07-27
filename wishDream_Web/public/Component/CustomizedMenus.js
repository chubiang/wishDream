import React from 'react'
import { withStyles } from '@material-ui/core/styles'
import { Menu, MenuItem, ListItemIcon, ListItemText, IconButton } from '@material-ui/core'
import { grey } from '@material-ui/core/colors'
import NotificationsIcon from '@material-ui/icons/Notifications'

import NoticeType from './NoticeType'

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
    marginRight: '8px'
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

  const menuItems = props.menus.map((obj, index) => {
    return ( 
      <StyledMenuItem key={'alarmItem' + index}>
        <ListItemIcon>
          <NoticeType type={obj.noticeType} />
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
