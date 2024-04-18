import { useForm, SubmitHandler } from "react-hook-form";
import { Login, Token } from "../models/auth.model";
import { CONTANTS } from "../utils/constants.util";
import { useNavigate } from "react-router-dom";

export default function LoginView() {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Login>({ mode: "onTouched" });

  const onSubmit: SubmitHandler<Login> = async (data) => {
    const result = await fetch(`${CONTANTS.apiUrl}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    const token = (await result.json()) as Token;
    localStorage.setItem("access_token", token.accessToken);
    navigate("/docs");
  };

  return (
    <div className="hero min-h-screen bg-base-200">
      <div className="hero-content flex-col lg:flex-row-reverse">
        <div className="text-center lg:text-left">
          <h1 className="text-5xl font-bold">Login now!</h1>
          <p className="py-6">
            Provident cupiditate voluptatem et in. Quaerat fugiat ut assumenda
            excepturi exercitationem quasi. In deleniti eaque aut repudiandae et
            a id nisi.
          </p>
        </div>
        <div className="card shrink-0 w-full max-w-sm shadow-2xl bg-base-100">
          <form className="card-body" onSubmit={handleSubmit(onSubmit)}>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Username</span>
              </label>
              <input
                type="text"
                placeholder="username"
                className={`input input-bordered ${errors.username ? "input-error" : ""}`}
                {...register("username")}
              />
              {errors.username && (
                <label className="label">
                  <span className="label-text-alt text-error">
                    This field is required
                  </span>
                </label>
              )}
            </div>
            <div className="form-control">
              <label className="label">
                <span className="label-text">Password</span>
              </label>
              <input
                type="password"
                placeholder="password"
                className={`input input-bordered ${errors.password ? "input-error" : ""}`}
                {...register("password")}
              />
              {errors.password && (
                <label className="label">
                  <span className="label-text-alt text-error">
                    This field is required
                  </span>
                </label>
              )}
              <label className="label">
                <a href="#" className="label-text-alt link link-hover">
                  Forgot password?
                </a>
              </label>
            </div>
            <div className="form-control mt-6">
              <button className="btn btn-primary">Login</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
