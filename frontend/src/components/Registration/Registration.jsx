import React from 'react';
import RegistrationProgress from './RegistrationProgress'
import RegistrationForm1 from './RegistrationForm1'
import RegistrationForm2 from './RegistrationForm2'
import RegistrationForm3 from './RegistrationForm3'
import RegistrationForm4 from './RegistrationForm4'
import Container from '@material-ui/core/Container';

class Registration extends React.Component {
  constructor(props) {
    super()
    this.handleRegistration1Update = this.handleRegistration1Update.bind(this);
    this.canProceed = this.canProceed.bind(this);
    this.handleNewStep = this.handleNewStep.bind(this);
    this.RegistrationForm1Ref = React.createRef();
    
    this.state = {
      currentRegistrationStep: 0,
      RegistrationForm1Props: {
        FirstName: "",
        LastName: "",
        Bio: "",
        Username: ""
      },
      previousRegistrationStep: -1,
      RegistrationForm1Ref: this.RegistrationForm1Ref,
      RegistrationForm2Props: {},
      RegistrationForm3Props: {},
      RegistrationForm4Props: {},
      registrationSteps: [
        <RegistrationForm1 ref={this.RegistrationForm1Ref} updateParent={this.handleRegistration1Update} />,
        <RegistrationForm2 />,
        <RegistrationForm3 />,
        <RegistrationForm4 />]
    }


  }
  handleRegistration1Update(e) {
    this.setState({ RegistrationForm1Props: e })
  }
  handleRegistration2Update(e) {
    this.setState({ RegistrationForm2Props: e })
  }
  handleRegistration3Update(e) {
    this.setState({ RegistrationForm3Props: e })
  }
  handleRegistration4Update(e) {
    this.setState({ RegistrationForm4Props: e })
  }

  handleNewStep(e) {
    this.setState({ currentRegistrationStep: e });
  }
  canProceed() {
    if (this.state.currentRegistrationStep === 0) {
      let props = this.state.RegistrationForm1Props;
      if (props.FirstName !== "" && props.LastName !== "" & props.Username !== "") {
        return true
      }
      return false;
    } else {
      return true
    }
  }
  componentDidMount() {
    this.RegistrationForm1Ref.current.updateValues(this.state.RegistrationForm1Props)
  }
  componentDidUpdate(prevProps, prevState) {
    if ( prevState.currentRegistrationStep !== 0  && this.state.currentRegistrationStep === 0) {
      this.RegistrationForm1Ref.current.updateValues(prevState.RegistrationForm1Props)
      console.log(prevState.RegistrationForm1Props)
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
