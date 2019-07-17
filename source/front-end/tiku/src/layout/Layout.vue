<template>
    <Framework v-bind:menu-info="mockData">
        <router-view :key="key" ></router-view>
    </Framework>
</template>

<script>
    import Framework from "../components/Framework";
    import mockData from "../data/mock/MenuInfoMock";
    export default {
        name: "Layout",
        data:function() {
            return {
                mockData
            };
        },
        computed: {
            key() {
                return this.$route.path
            }
        },
        components: {
            Framework,
        }
    }
</script>

<style scoped>

</style>