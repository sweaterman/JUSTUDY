import meetingEntrance from '../views/webRTC/meetingEntrance.vue';
import meetingRoom from '../views/webRTC/meetingRoom';
export default [
    {
        path: '/meeting',
        name: 'meetingEntrance',
        component: meetingEntrance
    },
    {
        path: '/meeting/room',
        name: 'meetingRoom',
        component: meetingRoom
    }
];
