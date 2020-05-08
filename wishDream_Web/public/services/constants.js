const DEV_MODE = true;
const HOST = '';
const DEV_HOST = 'http://localhost:8080/';
const CURRENT_HOST = DEV_MODE? DEV_HOST: HOST;

const Constants = {
    Host: CURRENT_HOST,
    Url: { 
        member: {
            login: 'login',
            logout: 'logout',
            username: '/username',
            oauth2Kakao: '/oauth2/authorization/kakao'
        },
        socket: {
            alarmList: 'alarmList'
        },
        tmp: {
            gridListData: 'json/tmpGridListData.json'
        }
    }
}
export default Constants;