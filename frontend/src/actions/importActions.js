import axios from "axios";
import { importUrl } from "../utils/const.js"

export const importIssues = (file, success, error) => {
    var bodyFormData = new FormData();
    bodyFormData.append('file', file);
    const config = {
          headers: {
            'content-type': 'multipart/form-data',
          },
    };
    axios.post(
        importUrl,
        bodyFormData,
        config,
    ).then(success)
        .catch(error);
}
