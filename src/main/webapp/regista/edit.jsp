<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../header.jsp" />
		<title>modifica elemento</title>
		
		<!-- style per le pagine diverse dalla index -->
	    <link href="./assets/css/global.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica Regista</h5> 
		    </div>
		    <div class='card-body'>

				<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
				<form method="post" action="ExecuteModificaRegistaServlet" novalidate="novalidate">
				
					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger">*</span></label>
							<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${registaDaModificare.nome }" required>
						</div>
						
						<div class="form-group col-md-6">
							<label>Cognome <span class="text-danger">*</span></label>
							<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${registaDaModificare.cognome }" required>
						</div>
					</div>
					
					<div class="form-row">	
						<div class="form-group col-md-6">
							<label>Nickname <span class="text-danger">*</span></label>
							<input type="text" class="form-control" name="nickName" id="nickName" placeholder="Inserire il nickname" value="${registaDaModificare.nickName }" required>
						</div>
						
						<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${registaDaModificare.dataDiNascita}' />
						<div class="form-group col-md-3">
							<label>Data di Nascita <span class="text-danger">*</span></label>
                       		<input class="form-control" id="dataDiNascita" type="date" placeholder="dd/MM/yy"
                           		title="formato : gg/mm/aaaa"  name="dataDiNascita" required 
                           		value="${parsedDate}" >
						</div>
						
						<div class="form-group col-md-3">
							<label for="sesso">Sesso <span class="text-danger">*</span></label>
						    <select class="form-control" id="sesso" name="sesso" required>
						    	<option value="" selected> - Selezionare - </option>
						      	<option value="MASCHIO" ${registaDaModificare.sesso == 'MASCHIO'?'selected':''} >M</option>
						      	<option value="FEMMINA" ${registaDaModificare.sesso == 'FEMMINA'?'selected':''} >F</option>
						    </select>
						</div>
						
					</div>
						
					<div class="form-group col-md-6">	
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
						<input type="hidden" name="idRegista" value="${registaDaModificare.id}">
						 <a href="ExecuteListRegistaServlet" class='btn btn-outline-secondary' style='width:80px'>
		                	<i class='fa fa-chevron-left'></i> Back
		        		 </a>
					</div>

				</form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	</body>
</html>