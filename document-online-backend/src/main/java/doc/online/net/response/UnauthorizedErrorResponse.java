package doc.online.net.response;

public class UnauthorizedErrorResponse extends BaseResponse {

	public UnauthorizedErrorResponse() {
		super();
		this.status = ResponseCode.UNAUTHORIZED;
	}

	public UnauthorizedErrorResponse(String msg) {
		this.status = ResponseCode.UNAUTHORIZED;
		this.message = msg;
	}
}
