<template>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <filter-bar style="width: 100%;margin: 0.5%;box-shadow: 5px 5px 5px grey"
                v-model="filterFcn" 
                v-bind:sortingOptions="sortingOptions" 
                v-bind:filterOptions="filterOptions"
                v-on:newSearch="performSearch">
                </filter-bar>
            </div>
            <div class="row">
                <project-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;"
                              v-for="project of displayProjects"
                              v-bind:key="project.id"
                              v-bind:projectId="project.id"
                              v-bind:projectName="project.name"
                              v-bind:projectStatus="project.effort"
                              v-bind:budget="project.budget">
                </project-card>
                <add-project-card style="margin: 0.5%;box-shadow: 5px 5px 5px grey;cursor:pointer;"></add-project-card>
            </div>
        </div>
    </div>
</template>
<script>
    import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
    import StatsCard from 'src/components/UIComponents/Cards/StatsCard.vue'
    import Card from 'src/components/UIComponents/Cards/Card.vue'
    import LTable from 'src/components/UIComponents/Table.vue'
    import Checkbox from 'src/components/UIComponents/Inputs/Checkbox.vue'
    import ProjectCard from 'src/components/UIComponents/Cards/ProjectCard.vue'
    import AddProjectCard from 'src/components/UIComponents/Cards/AddProjectCard.vue'
    import axios from 'axios'
    import ResourceBreakdown from 'src/components/UIComponents/PortfolioComponents/ResourceBreakdown.vue'
    import FilterBar from 'src/components/UIComponents/FilterBar.vue'

    export default {
        props: ['portfolioId'],
        components: {
            Checkbox,
            Card,
            LTable,
            ChartCard,
            StatsCard,
            ProjectCard,
            AddProjectCard,
            ResourceBreakdown,
            FilterBar
        },
        created() {
            this.fetchData();
        },
        data() {
            return {
                projects: [],
                displayProjects: [],
                sortBy: 'ID',
                sortingOptions: [
                    {value: 'id', text: 'ID'},
                    {value: 'name', text: 'Name'},
                    {value: 'effort', text: 'Status'},
                    {value: 'projectProgress', text: 'Completion Progress'},
                    {value: 'projectManager', text: 'Manager'},
                    {value: 'numPeopleOnTeam', text: 'Team Size'},
                    {value: 'startDate', text: 'Start Date'},
                    {value: 'endDate', text: 'End Date'},
                    {value: 'budget', text: 'Initial Budget'},
                    {value: 'budgetUsed', text: 'Budget Spent'}
                ],
                filterOptions: [
                    { value: {category: 'id', type: Number}, text: 'ID' },
                    { value: {category: 'name', type: String}, text: 'Name' },
                    { value: {category: 'effort', type: String}, text: 'Status' },
                    { value: {category: 'projectProgress', type: String}, text: 'Completion Progress' },
                    { value: {category: 'projectManager', type: String}, text: 'Manager' },
                    { value: {category: 'numPeopleOnTeam', type: Number}, text: 'Team Size' },
                    { value: {category: 'startDate', type: Date}, text: 'Start Date' },
                    { value: {category: 'endDate', type: Date}, text: 'End Date' },
                    { value: {category: 'budget', type: Number}, text: 'Budget' },
                    { value: {category: 'budgetUsed', type: Number}, text: 'Budget Used' }
                ],
                filterFcn: function (list) { return list;}
            }
        },
        methods: {
            fetchData() {
                var info = this;
                axios.get(this.$root.serverURL + "/api/projects")
                .then(response => {
                    info.projects = response.data;
                    info.displayProjects = response.data;
                })
            },
            performSearch() {
                this.displayProjects = this.filterFcn(this.projects);
            }
        }
    }
</script>
<style scoped>
    div.filters {
        text-align: center;
        width: 100%;
        padding: 1em;
        border: 1px solid grey;
        border-radius: 2px;
        margin-bottom: 1em;
        color: white;
        background-color: grey;
        margin: 0.5%;
        box-shadow: 5px 5px 5px grey;
    }
</style>
