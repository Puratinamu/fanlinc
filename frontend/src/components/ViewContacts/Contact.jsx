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
            <TableRow key={this.state.username} className="contact">
                <TableCell>{this.state.username}</TableCell>
                <TableCell align="center" colspan="3">{this.state.description}</TableCell>
                <TableCell align="right">{this.state.email}</TableCell>
            </TableRow>
        );
    }
}

export default Contact;