import Vue from 'vue';
import Vuetify from 'vuetify/lib';



Vue.use(Vuetify);

Vue.use(require('vue-moment'));

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#0D875C',
                secondary: '#F5F5EC',
                success: '#0D875C',
                danger: "#FC887C",
                error: "FC887C",
                warning: "#FF9300"
            }
        }
    }
});
