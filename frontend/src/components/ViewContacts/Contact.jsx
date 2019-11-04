import React from "react";

const UserName = "Username: ";
const Description = "Description: ";
const Email = "Email: ";

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
        return (
            <div className="contact">
                <span>{UserName}{this.state.username},</span>
                <span>   </span>
                <span>{Description}{this.state.description},</span>
                <span>   </span>
                <span>{Email}{this.state.email},</span>
            </div>
        );
    }
}

export default Contact;