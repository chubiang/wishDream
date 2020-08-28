const DEV_MODE = true;
const HOST = '';
const DEV_HOST = 'http://localhost:8030/';
const WEBSOCKET_HOST = 'ws://localhost:8030';
const CURRENT_HOST = DEV_MODE? DEV_HOST: HOST;

const Constants = {
    Host: CURRENT_HOST,
    WebSocket:WEBSOCKET_HOST,
    Url: { 
        member: {
            login: 'login',
            logout: 'logout',
            username: '/username',
            oauth2Kakao: '/oauth2/authorization/kakao',
            join: 'join',
            menu: 'menu',
        },
        pet: {
            breed: '/json/petBreed',
            dailyLife: '/pet/dailyLife/grid',
        },
        socket: {
            alarmList: '/topic/alarmList',
            kafkaAlarm: '/topic/kafkaAlarm'
        },
        tmp: {
            gridListData: 'json/tmpGridListData.json'
        }
    }
}
export default Constants;