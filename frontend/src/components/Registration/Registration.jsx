import React from 'react';
import RegistrationProgress from './RegistrationProgress'
import RegistrationForm1 from './RegistrationForm1'
import RegistrationForm2 from './RegistrationForm2'
import RegistrationForm3 from './RegistrationForm3'
import RegistrationForm4 from './RegistrationForm4'
import RegistrationCompletion from './RegistrationCompletion'
import Container from '@material-ui/core/Container';

class Registration extends React.Component {
  constructor(props) {
    super()
    this.handleRegistration1Update = this.handleRegistration1Update.bind(this);
    this.canProceed = this.canProceed.bind(this);
    this.handleNewStep = this.handleNewStep.bind(this);
    this.RegistrationForm1Ref = React.createRef();

    this.state = {
      RegistrationForm1Ref: this.RegistrationForm1Ref,
      currentRegistrationStep: 0,
      RegistrationFormProps: {
        firstName: "",
        lastName: "",
        bio: "",
        username: "",
        passwordValue: "",
        confirmPasswordValue: "",
        errorState: true,
        email: ""
      },
      RegistrationCompletionProps: {},
      registrationSteps: [
        <RegistrationForm1 ref={this.RegistrationForm1Ref} updateParent={this.handleRegistration1Update} />,
        <RegistrationForm2 />,
        <RegistrationForm3 />,
        <RegistrationForm4 {...this.RegistrationForm4Props} />,
        <RegistrationCompletion />
      ]
    }


  }
  handleRegistration1Update(e) {
    this.setState({ RegistrationFormProps: e })
  
    this.state.registrationSteps[3] = <RegistrationForm4 {...this.state.RegistrationFormProps} />
    this.state.registrationSteps[4] = <RegistrationCompletion {...this.state.RegistrationFormProps}  />
  
  }

  handleNewStep(e) {
    this.setState({ currentRegistrationStep: e });
  }
  canProceed() {
    if (this.state.currentRegistrationStep === 0) {
      let props = this.state.RegistrationFormProps;
      if (
        props.firstName !== "" &&
        props.lastName !== "" &&
        props.username !== "" &&
        !props.errorState &&
        props.passwordValue !== "" &&
        props.confirmPasswordValue !== "" &&
        props.confirmPasswordValue === props.passwordValue &&
        props.email !== ""

      ) {
        return true
      }
      return false;
    }else if(this.state.currentRegistrationStep === 4){
      return false
    }
     else {
      return true
    }
  }
  componentDidMount() {
    this.RegistrationForm1Ref.current.updateValues(this.state.RegistrationFormProps)

  }
  componentDidUpdate(prevProps, prevState) {
    if (prevState.currentRegistrationStep !== 0 && this.state.currentRegistrationStep === 0) {
      this.RegistrationForm1Ref.current.updateValues(prevState.RegistrationFormProps)
    }
    
  }

  render() {
    return (
      <Container maxWidth="md" >
        <RegistrationProgress callback={this.handleNewStep} canProceed={this.canProceed()} />
        {this.state.registrationSteps[this.state.currentRegistrationStep]}

      </Container>
    );
  }
}

export default Registration;
