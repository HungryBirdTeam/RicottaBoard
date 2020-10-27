<template>
    <div id="markdown" class="MoveableBox">
        <input type="text" placeholder="제목" class="title" v-model="title">
        <Editor        
            height="500px"
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
        markdown: Object,
    },
    methods: {
        onEditorChange() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            this.text = content
            console.log('editor text!', this.text)
        },
    }
}
</script>

<style>
html, body, #markdown {
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