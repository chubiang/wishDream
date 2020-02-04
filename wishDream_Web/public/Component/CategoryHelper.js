import Typography from '@material-ui/core/Typography'
import { makeStyles, useTheme } from '@material-ui/styles'


const Helper = ({menus}) => menus.map((obj, index) => {
    return (
        <p>
            <span>menu</span>
            <span>></span>
        </p>
    )
})

const CategoryHelper = (props) => {

    

    return (
        <div>
            <Helper menus={props.menus} />
        </div>
    )
}

export default CategoryHelper