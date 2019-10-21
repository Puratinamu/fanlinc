import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles(theme => ({
  container: {
    alignContent: 'center'

  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
  },
  textLabel: {
    marginTop: theme.spacing(3),

  }

}));

function TextBox(props) {
  return <TextField
    {...props.textBoxProps}
    margin="normal"
    variant="outlined"
  />

}

function CenteredTextFieldElement(props) {
  const classes = useStyles();
  return (
    <Box textAlign="center">
      <Box display="flex">
        <Typography component="div" fontWeight="fontWeightLight" className={classes.textLabel}>
          {props.textLabel}
        </Typography>
        <Box marginLeft="auto">
          <TextBox textBoxProps={props.textBoxProps} />
        </Box>
      </Box>
    </Box>
  )
}



function FirstNameField(props) {
  return (
    <CenteredTextFieldElement
      textLabel="First Name"
      textBoxProps={
        {
          required: true,
          id: "first-name",
          label: "First Name",
          placeholder: "John",
          value: props.value,
          onChange: props.onChange
        }
      }
    />
  )
}

function LastNameField(props) {
  return (
    <CenteredTextFieldElement
      textLabel="Last Name"
      textBoxProps={
        {
          required: true,
          id: "last-name",
          label: "Last name",
          placeholder: "Doe",
          value: props.value,

          onChange: props.onChange

        }
      }
    />
  )
}

function UserNameField(props) {
  return (
    <CenteredTextFieldElement
      textLabel="Username"
      textBoxProps={
        {
          required: true,
          id: "user-name",
          label: "Username",
          placeholder: "JohnDoe123",
          value: props.value,

          onChange: props.onChange


        }
      }
    />
  )
}

class PasswordEntry extends React.Component {
  constructor() {
    super();
    this.state = {
      passwordValue: null,
      confirmPasswordValue: null,
      errorState: false,
      firstPassPassword: true,
      firstPassConfirmPassword: true
    }
    this.handlePasswordUpdate = this.handlePasswordUpdate.bind(this);
    this.handleConfirmPasswordUpdate = this.handleConfirmPasswordUpdate.bind(this);
    this.handlePasswordMouseLeave = this.handlePasswordMouseLeave.bind(this);
    this.handleConfirmPasswordMouseLeave = this.handleConfirmPasswordMouseLeave.bind(this);
    this.checkPasswords = this.checkPasswords.bind(this);

  }

  handlePasswordUpdate(event) {
    this.setState(
      { passwordValue: event.target.value },
      this.checkPasswords
    )

  }

  handleConfirmPasswordUpdate(event) {
    this.setState(
      { confirmPasswordValue: event.target.value },
      this.checkPasswords
    )
  }

  handlePasswordMouseLeave() {
    if (this.state.firstPassPassword === true && this.state.passwordValue !== null) {
      this.setState(
        { firstPassPassword: false },
        this.checkPasswords
      )
    } else {
      this.checkPasswords()
    }
  }

  handleConfirmPasswordMouseLeave() {
    if (this.state.firstPassConfirmPassword === true && this.state.confirmPasswordValue !== null) {
      this.setState(
        { firstPassConfirmPassword: false },
        this.checkPasswords
      )
    } else {
      this.checkPasswords()
    }
  }

  checkPasswords() {
    if (
      !this.state.firstPassConfirmPassword &&
      !this.state.firstPassPassword &&
      this.state.passwordValue !== this.state.confirmPasswordValue) {
      this.setState({ errorState: true })
    } else {
      this.setState({ errorState: false })
    }
  }

  render() {
    return (
      <React.Fragment>
        <PasswordField
          onChange={this.handlePasswordUpdate}
          error={this.state.errorState}
          onMouseLeave={this.handlePasswordMouseLeave} />

        <ConfirmPasswordField
          onChange={this.handleConfirmPasswordUpdate}
          error={this.state.errorState}
          onMouseLeave={this.handleConfirmPasswordMouseLeave} />
      </React.Fragment>
    )
  }
}

function PasswordField(props) {
  return (
    <CenteredTextFieldElement
      textLabel="Password"
      textBoxProps={
        {
          required: true,
          id: "password",
          label: "Password",
          type: "password",
          onChange: props.onChange,
          error: props.error,
          onMouseLeave: props.onMouseLeave
        }
      }
    />
  )
}

function ConfirmPasswordField(props) {

  return (
    <CenteredTextFieldElement
      textLabel="Confirm Password"
      textBoxProps={
        {
          required: true,
          id: "confirm-password",
          label: "Confirm Password",
          type: "password",
          onChange: props.onChange,
          error: props.error,
          onMouseLeave: props.onMouseLeave
        }
      }
    />
  )
}


function BioField(props) {
  return (
    <CenteredTextFieldElement
      textLabel="Bio"
      textBoxProps={
        {
          id: "bio",
          label: "Bio",
          placeholder: "Tell us about yourself",
          multiline: true,
          rows: "5",
          onChange: props.onChange

        }
      }
    />
  )
}

class RegistrationForm1 extends React.Component {

  constructor(props) {

    super()

    this.handleFirstNameChange = this.handleFirstNameChange.bind(this);
    this.handleLastNameChange = this.handleLastNameChange.bind(this);
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handleBioChange = this.handleBioChange.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.updateValues = this.updateValues.bind(this);
   
    this.state = {
      FirstName: "",
      LastName: "",
      Bio: "",
      Username: ""
    }
  }
  updateValues(e) {
    this.setState({
      FirstName: e.FirstName,
      LastName: e.LastName,
      Bio: e.Bio,
      Username: e.Username
    }
    
  )
  console.log(e)
}
handleChange(e) {
  this.props.updateParent(this.state)
}
handleFirstNameChange(e) {
  this.setState({ FirstName: e.target.value })
}
handleLastNameChange(e) {
  this.setState({ LastName: e.target.value })
}
handleUsernameChange(e) {
  this.setState({ Username: e.target.value })
}
handleBioChange(e) {
  this.setState({ Bio: e.target.value })
}

render() {
  return (
    <form noValidate onChange={this.handleChange} autoComplete="off">
      <Box display="flex" textAlign="center">
        <Box margin="0 auto" width="44%">
          <FirstNameField onChange={this.handleFirstNameChange} value={this.state.FirstName} />
          <LastNameField onChange={this.handleLastNameChange} value={this.state.LastName} />
          <UserNameField onChange={this.handleUsernameChange} value={this.state.Username} />
          <PasswordEntry value={this.props.PasswordEntry} />
          <BioField onChange={this.handleBioChange} value={this.props.Bio} />
        </Box>
      </Box>
    </form >
  );
}
}
export default RegistrationForm1

