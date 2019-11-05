import React from "react";
import Contact from "./Contact";
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import './styles.scss';

const UserName = "Username";
const Description = "Description";
const Email = "Email";

class ContactsList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            contacts: props.contacts
        };
    }

    render() {
        this.state.contacts = [{"username": "test1", "description": "A dude", "email": "lol@hotamil.com"},
            {"username": "test2", "description": "A asd", "email": "lolasdasd@hotamil.com"}];
        const listItems = this.state.contacts.map(d => <Contact username={d.username}
                                                                description={d.description}
                                                                email={d.email}/>);

        // return (
        //     <div>
        //         {listItems}
        //     </div>
        // );
        return (
            <React.Fragment>
                {/*<Title>Contact List</Title>*/}
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell >{UserName}</TableCell>
                            <TableCell align="center" colspan="3">{Email}</TableCell>
                            <TableCell align="right">{Description}</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody >
                        {listItems}
                    </TableBody>
                </Table>
            </React.Fragment>
        );
    }
}

export default ContactsList;
