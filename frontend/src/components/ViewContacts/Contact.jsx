import React from "react";
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import './styles.scss';



class Contact extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            id: props.id,
            username: props.username,
            description: props.description,
            email: props.email
        };
    }

    render() {
        // return (
        //     <div className="contact">
        //         <span>
        //             {UserName}{this.state.username}
        //             <br/>
        //         </span>
        //         <span>
        //             {Description}{this.state.description}
        //             <br/>
        //         </span>
        //         <span>
        //             {Email}{this.state.email}
        //             <br/>
        //         </span>
        //     </div>
        // );
        return (
            <TableRow key={this.state.username} className="contact" tabIndex={-1}>
                <TableCell>{this.state.username}</TableCell>
                <TableCell colSpan="4">{this.state.description}</TableCell>
                <TableCell >{this.state.email}</TableCell>
            </TableRow>
        );
    }
}

export default Contact;