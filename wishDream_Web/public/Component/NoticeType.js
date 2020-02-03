import React from 'react'
import InboxIcon from '@material-ui/icons/MoveToInbox'
import DraftsIcon from '@material-ui/icons/Drafts'
import SendIcon from '@material-ui/icons/Send'
import MessageIcon from '@material-ui/icons/Message'
import AnnouncementIcon from '@material-ui/icons/Announcement'


const NoticeType = React.forwardRef(({type}, ref) => 
    {
        console.log('type',type);
        
        switch (type) {
            case 1:
                return (<SendIcon ref={ref}/>)
            case 2:
                return (<DraftsIcon ref={ref}/>)
            case 3:
                return (<MessageIcon ref={ref}/>)
            case 4:
                return (<InboxIcon ref={ref}/>)
            default:
                return (<AnnouncementIcon ref={ref}/>)
        }
    }
);
export default NoticeType;