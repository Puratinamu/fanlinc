import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepButton from '@material-ui/core/StepButton';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';

const useStyles = makeStyles(theme => ({
  root: {
    textAlign: "center"
  },
  completed: {
    display: 'inline-block',
  },
  instructions: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1),
  },
  button: {
    maxHeight: 40
  },
  stepper: {
    'background-color': '#fafafa'
  },
    loginButton: {
        'margin-right': 32
    }
}));

function getSteps() {
  return ['Profile', 'Profile Image', 'Fandom & Feed', 'Final Step'];
}


export default function RegistrationProgress(props) {
  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);
  const [completed, setCompleted] = React.useState({});

  const steps = getSteps();

  const totalSteps = () => {
    return steps.length;
  };

  const completedSteps = () => {
    return Object.keys(completed).length;
  };

  const isLastStep = () => {
    return activeStep === totalSteps() - 1;
  };

  const allStepsCompleted = () => {
    return completedSteps() === totalSteps();
  };

  const handleNext = () => {
    const newCompleted = completed;
    newCompleted[activeStep] = true;
    setCompleted(newCompleted);
    const newActiveStep =
      isLastStep() && !allStepsCompleted()
        ? // It's the last step, but not all steps have been completed,
        // find the first step that has been completed
        steps.findIndex((step, i) => !(i in completed))
        : activeStep + 1;
    setActiveStep(newActiveStep);
    props.callback(newActiveStep)
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
    props.callback(activeStep - 1)
  };

  const handleStep = step => () => {
    console.log(completed)
    if ((step <= activeStep || completed[step]) && activeStep !== 4) {
      setActiveStep(step);
      props.callback(step);
    }
  };

  return (
    <Box className={classes.root}>
      <Box display="flex" alignItems="center" justifyContent="center">
        <Button
          variant="contained"
          color="primary" href="/login" className={classes.loginButton}>
          Back To Login
        </Button>
        <Button
          variant="contained"
          color="primary" disabled={activeStep === 0 || activeStep === 4} onClick={handleBack} className={classes.button}>
          Back
        </Button>
        <Stepper className={classes.stepper} nonLinear activeStep={activeStep}>
          {steps.map((label, index) => (
            <Step key={label}>
              <StepButton onClick={handleStep(index)} completed={completed[index]}>
                {label}
              </StepButton>
            </Step>
          ))}
        </Stepper>
        <Button
          variant="contained"
          color="primary"
          onClick={handleNext}
          className={classes.button}
          disabled={!props.canProceed}
        >
          Next
        </Button>
      </Box>
    </Box>
  );
}
