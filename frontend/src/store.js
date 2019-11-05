// from https://itnext.io/manage-react-state-without-redux-a1d03403d360

import React from 'react'
import cookieManager from './cookieManager'
const StoreContext = React.createContext()
const createStore = WrappedComponent => {
  return class extends React.Component {
    state = {
      get: key => {
        return this.state[key]
      },
      set: (key, value) => {
        const state = this.state
        state[key] = value
        this.setState(state)
      },
      remove: key => {
        const state = this.state
        delete state[key]
        this.setState(state)
      },
      'authenticatedUserEmail':cookieManager.getCookie("authenticatedUserEmail"),
      'sessionToken':cookieManager.getCookie("sessionToken"),
      'isLoggedIn':cookieManager.getCookie("authenticatedUserEmail") !== ""
    }
    render() {
      return (
        <StoreContext.Provider value={this.state}>
          <WrappedComponent {...this.props} />
        </StoreContext.Provider>
      )
    }
  }
}
const withStore = WrappedComponent => {
  return class extends React.Component {
    render() {
      return (
        <StoreContext.Consumer>
          {context => <WrappedComponent store={context} {...this.props} />}
        </StoreContext.Consumer>
      )
    }
  }
}
export { createStore, withStore }