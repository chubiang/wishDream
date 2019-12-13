const DEV_MODE = true;
const HOST = '';
const DEV_HOST = 'http://localhost:8080/';
const CURRENT_HOST = DEV_MODE? DEV_HOST: HOST;

const Constants = {
    Url: { 
        member: {
            login: 'login'
        }
    }
}
export default Constants;