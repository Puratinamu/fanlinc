let redirectManager = {

    reloadPage: function(){
        window.location.reload();
    },
    goTo: function(path){
        window.location.href = `/${path}`;
    },
    getUrlVars : function () {
        var vars = {};
        window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    },
    getUrlParam: function(parameter, defaultvalue){
        var urlparameter = defaultvalue;
        if(window.location.href.indexOf(parameter) > -1){
            urlparameter = this.getUrlVars()[parameter];
            }
        return urlparameter;
    },
   
    handleRedirect: function(){
        this.goTo(this.getUrlParam("redirect") === undefined ? "" : this.getUrlParam("redirect"))
    },
    getCurrentPath: function(){
        return window.location.pathname.substring(1)
    }
}
export default redirectManager