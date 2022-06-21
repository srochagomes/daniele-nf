import TableView from '../component/table/TableView';
import FileUploadPage from '../component/upload/FileUploadPage';
import './../App.css';

function FormUpload() {
  return (
    <>
    <div className="App">
      <h1>UPLOAD DE ARQUIVOS NF-E</h1>
      <h2>Aplicação para teste!</h2>
      <FileUploadPage/>   
      <TableView />     
    </div>
    </>

    

  );
}
export default FormUpload;


