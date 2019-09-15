import axios from "axios";
import { allIssueUrl } from "../utils/const.js"

export const findAllIssues = (success, error) => {
    axios.get(allIssueUrl)
        .then(success)
        .catch(error);
}