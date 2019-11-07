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
import TablePagination from "@material-ui/core/TablePagination";
import Paper from "@material-ui/core/Paper";
import IconButton from "@material-ui/core/IconButton";
import {KeyboardArrowLeft, KeyboardArrowRight} from "@material-ui/icons";
import LastPageIcon from '@material-ui/icons/LastPage';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import makeStyles from "@material-ui/core/styles/makeStyles";
import useTheme from "@material-ui/core/styles/useTheme";


const UserName = "Username";
const Description = "Description";
const Email = "Email";

const useStyles1 = makeStyles(theme => ({
    root: {
        flexShrink: 0,
        marginLeft: theme.spacing(2.5),
    },
}));

function TablePaginationActions(props) {
    const classes = useStyles1();
    const theme = useTheme();
    const { count, page, rowsPerPage, onChangePage } = props;

    const handleFirstPageButtonClick = event => {
        onChangePage(event, 0);
    };

    const handleBackButtonClick = event => {
        onChangePage(event, page - 1);
    };

    const handleNextButtonClick = event => {
        onChangePage(event, page + 1);
    };

    const handleLastPageButtonClick = event => {
        onChangePage(event, Math.max(0, Math.ceil(count / rowsPerPage) - 1));
    };

    return (
        <div className={classes.root}>
            <IconButton
                onClick={handleFirstPageButtonClick}
                disabled={page === 0}
                aria-label="first page"
            >
                {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
            </IconButton>
            <IconButton onClick={handleBackButtonClick} disabled={page === 0} aria-label="previous page">
                {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
            </IconButton>
            <IconButton
                onClick={handleNextButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="next page"
            >
                {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
            </IconButton>
            <IconButton
                onClick={handleLastPageButtonClick}
                disabled={page >= Math.ceil(count / rowsPerPage) - 1}
                aria-label="last page"
            >
                {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
            </IconButton>
        </div>
    );
}

const useStyles2 = makeStyles(theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing(3),
    },
    table: {
        minWidth: 500,
    },
    tableWrapper: {
        overflowX: 'auto',
    },
}));

export default function ContactsList() {
    const classes = useStyles2();
    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    // const emptyRows = rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = event => {
        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const contacts = [
            {"id": 1, "username": "Carla99", "description": "A super nice person", "email": "carla@hotamil.com"},
            {"id": 2,"username": "Pal", "description": "Likes to league of legends!", "email": "bobmom@gmail.com"},
            {"id": 3,"username": "abbas22", "description": "Worked at NVIDIA", "email": "abbas@gmail.com"},
            {"id": 4,"username": "bob1", "description": "amazing", "email": "bob1@hotamil.com"},
            {"id": 5,"username": "bob2", "description": "super", "email": "bob2@gmail.com"},
            {"id": 6,"username": "bob3", "description": "nice", "email": "bob3@gmail.com"},
            {"id": 7,"username": "bob4", "description": "wow", "email": "bob4@hotamil.com"},
            {"id": 8,"username": "bob5", "description": "I like food", "email": "bob5@gmail.com"},
            {"id": 9,"username": "niceguy24", "description": "lol", "email": "reallynice@gmail.com"},
            {"id": 10,"username": "hotturbanGG", "description": "Government worker", "email": "simranpreet@gmail.com"},
            {"id": 10,"username": "coolguy16", "description": "\"Why so serious?\"", "email": "coolguy@gmail.com"},
    ];

    return (
        <Paper className={classes.root}>
            <div className={classes.tableWrapper}>
                <div className={classes.tableWrapper}>
                    <Table size="medium" stickyHeader aria-label="sticky table">
                        <TableHead>
                            <TableRow >
                                <TableCell >{UserName}</TableCell>
                                <TableCell colSpan="4">{Description}</TableCell>
                                <TableCell>{Email}</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody >
                            {contacts.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map(row =>
                                    <Contact id={row.id}
                                            username={row.username}
                                             description={row.description}
                                             email={row.email}/>
                            )}
                        </TableBody>
                    </Table>
                </div>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 100]}
                    component="div"
                    count={contacts.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    backIconButtonProps={{
                        'aria-label': 'previous page',
                    }}
                    nextIconButtonProps={{
                        'aria-label': 'next page',
                    }}
                    SelectProps={{
                        inputProps: { 'aria-label': 'rows per page' },
                        native: true,
                    }}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                    ActionsComponent={TablePaginationActions}
                />
            </div>
        </Paper>
    );
}

// class ContactsList extends React.Component {
//     constructor(props) {
//         super(props);
//
//         this.state = {
//             page: 0,
//             rowsPerPage: 10,
//             contacts: props.contacts
//         };
//
//         this.setPage = this.setPage.bind(this);
//         this.setRowsPerPage = this.setRowsPerPage.bind(this);
//     }
//
//
//
//     setPage(selection) {
//         this.setState({
//             page: selection,
//         });
//         // this.props.onFetchPage(this.props.status, selection, this.state.pageSize);
//     }
//
//     setRowsPerPage(selection) {
//         this.setState({
//             rowsPerPage: selection,
//         });
//     }
//
//     handleChangePage = (event, newPage) => {
//         this.setPage(newPage);
//     };
//
//     handleChangeRowsPerPage = event => {
//         this.setRowsPerPage(+event.target.value);
//         this.setPage(0);
//     };
//
//
//     createData(id, username, description, email) {
//         return {id, username, description, email};
//     };
//     // const rows = [
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"Carla99", "A super nice person", "carla@hotamil.com"),
//     //     this.createData(1,"last", "A super nice person", "carla@hotamil.com")
//     // ];
//
//
//     render() {
//         const { classes } = this.props;
//         const contacts = [
//             {"id": 1, "username": "Carla99", "description": "A super nice person", "email": "carla@hotamil.com"},
//             {"id": 2,"username": "Pal", "description": "Likes to league of legends!", "email": "bobmom@gmail.com"},
//             {"id": 3,"username": "abbas22", "description": "Worked at NVIDIA", "email": "abbas@gmail.com"},
//             {"id": 4,"username": "bob1", "description": "amazing", "email": "bob1@hotamil.com"},
//             {"id": 5,"username": "bob2", "description": "super", "email": "bob2@gmail.com"},
//             {"id": 6,"username": "bob3", "description": "nice", "email": "bob3@gmail.com"},
//             {"id": 7,"username": "bob4", "description": "wow", "email": "bob4@hotamil.com"},
//             {"id": 8,"username": "bob5", "description": "I like food", "email": "bob5@gmail.com"},
//             {"id": 9,"username": "niceguy24", "description": "lol", "email": "reallynice@gmail.com"},
//             {"id": 10,"username": "hotturbanGG", "description": "Government worker", "email": "simranpreet@gmail.com"},
//             {"id": 10,"username": "coolguy16", "description": "\"Why so serious?\"", "email": "coolguy@gmail.com"},
//         ];
//
//
//
//         const listItems = contacts.map(d => <Contact id={d.id} username={d.username}
//                                                                 description={d.description}
//                                                                 email={d.email}/>);
//
//         return (
//             <Paper className={classes.root}>
//                 <div className={classes.tableWrapper}>
//                     {/*<Title>Contact List</Title>*/}
//                     <Table size="medium" stickyHeader aria-label="sticky table">
//                         <TableHead>
//                             <TableRow >
//                                 <TableCell >{UserName}</TableCell>
//                                 <TableCell colSpan="4">{Description}</TableCell>
//                                 <TableCell>{Email}</TableCell>
//                             </TableRow>
//                         </TableHead>
//                         <TableBody >
//                             {contacts.slice(this.state.page * this.state.rowsPerPage,
//                                 this.state.page * this.state.rowsPerPage + this.state.rowsPerPage).map(row => {
//                                 return (
//                                     <Contact id={row.id}
//                                             username={row.username}
//                                              description={row.description}
//                                              email={row.email}/>
//                                 );
//                             })}
//                             {/*{listItems}*/}
//                         </TableBody>
//                     </Table>
//                 </div>
//                 <TablePagination
//                     rowsPerPageOptions={[10, 25, 100]}
//                     component="div"
//                     count={contacts.length}
//                     rowsPerPage={this.state.rowsPerPage}
//                     page={this.state.page}
//                     backIconButtonProps={{
//                         'aria-label': 'previous page',
//                     }}
//                     nextIconButtonProps={{
//                         'aria-label': 'next page',
//                     }}
//                     onChangePage={this.handleChangePage}
//                     onChangeRowsPerPage={this.handleChangeRowsPerPage}
//                     ActionsComponent={TablePaginationActions}
//                 />
//             </Paper>
//             // </React.Fragment>
//         );
//     }
// }

// export default ContactsList;
