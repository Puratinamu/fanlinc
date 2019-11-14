import React from 'react';
import ContactList from '../../components/ViewContacts/index.jsx'
import {withStore} from '../../store'

class ViewContacts extends React.Component{

    render(){
        return <ContactList store={this.props.store} />
    }
}
export default withStore(ViewContacts);