import React, {useState} from 'react';
import RegistrationProgress from './RegistrationProgress/RegistrationProgress'
import RegistrationForm1 from './RegistrationForm1/RegistrationForm1'
import RegistrationForm2 from './RegistrationForm2/RegistrationForm2'
import RegistrationForm3 from './RegistrationForm3/RegistrationForm3'
import RegistrationForm4 from './RegistrationForm4'
import Container from '@material-ui/core/Container';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(theme => ({
  container: {
    //backgroundColor:"cfe8fc"

  }
}));

export default function Registration() {
  const classes = useStyles();
  const registrationSteps = [<RegistrationForm1 />, <RegistrationForm2/>, <RegistrationForm3/>, <RegistrationForm4/>]
  const [currentRegistrationStep, setCurrentRegistrationStep] = useState(registrationSteps[0]);

  function handleNewStep(e){
    setCurrentRegistrationStep(registrationSteps[e]);
  }
  return (
      <Container maxWidth="md" className={classes.container} >
        <RegistrationProgress callback={handleNewStep}/>
        {currentRegistrationStep}
      </Container>
  );
}