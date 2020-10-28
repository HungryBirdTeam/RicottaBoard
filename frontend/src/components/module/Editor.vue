<template>
    <div id="EditorMain" class="MoveableBox">
        <div class="title row m-0">
            <input type="text" placeholder="제목" class="col-10 p-0" v-model="editor.title">
            <v-btn
                
                color="blue-grey"
                class="ma-2 white--text"
                @click="loader = 'loading3'"
                >
                저장하기
                <v-icon
                    right
                    dark
                >
                    mdi-cloud-download
                </v-icon>
            </v-btn>
        </div>
        <Editor        
            height="500px"
            :initialValue="editor.text"
            ref="toastuiEditor"
            @change = "onEditorChange"
            class="bg-white"
        />
    </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script src="lodash.js"></script>
<script>
import _ from "lodash"
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/vue-editor';

export default {
    data() {
        return {
            title: '', 
            text: '',
        }
    },
    components: {
        Editor
    },
    props: {
        editor: Object,
    },
    methods: {
        onEditorChange() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            this.editor.text = content
            console.log('editor text!', this.editor.text)
        },
    }
}
</script>

<style scoped>
html, body, #EditorMain {
    margin: 0;
    width: 860px;
    height: 610px;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #333;
    background-color: #f6f6f6;
}

.title {
    width: 800px;
    height: 50px;
    border: 1px solid #ccc;
    background-color: #f6f6f6;
}
</style>