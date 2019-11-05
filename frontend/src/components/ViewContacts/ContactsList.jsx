import React from "react";
import Contact from "./Contact";

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

        return (
            <div>
                {listItems}
            </div>
        );
    }
}

export default ContactsList;
