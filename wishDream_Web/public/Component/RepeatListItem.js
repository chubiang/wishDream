import React, { useState } from 'react'
import axios from 'axios'
import ListItem from '@material-ui/core/ListItem'
import ListItemIcon from '@material-ui/core/ListItemIcon'
import ListItemText from '@material-ui/core/ListItemText'
import InboxIcon from '@material-ui/icons/MoveToInbox'
import MailIcon from '@material-ui/icons/Mail'
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import { Link } from 'react-router-dom'

const itemText = {
    textAlign: 'left'
}

const RepeatListItem = React.forwardRef(({item, index}, ref) => {

    const [itemAlign, setItemAlign] = useState(itemText);
    const goMenu = (item) => {

    }

    if (item.menuOrder > 0) { 
        return (
            <Link to={item.menuPath}>
                <ListItem button key={'menu'+ index} ref={ref}>
                    <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>
                    <ListItemText primary={item.name} />
                </ListItem>
            </Link>
        )
    } else {
        return (
            <ListItem button key={'menu'+ index} ref={ref}>
                <ListItemIcon><KeyboardArrowDownIcon /></ListItemIcon>
                <ListItemText primary={item.name} style={itemAlign} />
            </ListItem>
        )
    }
});

export default RepeatListItem;