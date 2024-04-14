import { $, component$ } from "@builder.io/qwik";
import { routeLoader$, type DocumentHead } from "@builder.io/qwik-city";
import type { SubmitHandler } from "@modular-forms/qwik";
import { useForm, valiForm$, type InitialValues } from "@modular-forms/qwik";
import type { Input } from "valibot";
import { email, minLength, object, string } from "valibot";

const LoginSchema = object({
  email: string([
    minLength(1, "Please enter your email."),
    email("The email address is badly formatted."),
  ]),
  password: string([
    minLength(1, "Please enter your password."),
    minLength(8, "Your password must have 8 characters or more."),
  ]),
});

type LoginForm = Input<typeof LoginSchema>;

export const useFormLoader = routeLoader$<InitialValues<LoginForm>>(() => ({
  email: "",
  password: "",
}));

export default component$(() => {
  const [loginForm, { Form, Field }] = useForm<LoginForm>({
    loader: useFormLoader(),
    validate: valiForm$(LoginSchema),
    validateOn: "touched",
  });

  const handleSubmit = $<SubmitHandler<LoginForm>>((values, event) => {
    event.preventDefault();
    // Runs on client
    console.log(values);
  });

  return (
    <div class="hero min-h-screen bg-base-200">
      <div class="hero-content flex-col lg:flex-row-reverse">
        <div class="card shrink-0 w-full max-w-sm shadow-2xl bg-base-100">
          <Form class="card-body" onSubmit$={handleSubmit}>
            <Field name="email">
              {(field, props) => (
                <div class="form-control">
                  <label class="label">
                    <span class="label-text">Email</span>
                  </label>
                  <input
                    {...props}
                    type="email"
                    placeholder="email"
                    class="input input-bordered"
                    value={field.value}
                  />
                  {field.error && (
                    <div class="label">
                      <span class="label-text-alt text-error">
                        {field.error}
                      </span>
                    </div>
                  )}
                </div>
              )}
            </Field>
            <Field name="password">
              {(field, props) => (
                <div class="form-control">
                  <label class="label">
                    <span class="label-text">Password</span>
                  </label>
                  <input
                    {...props}
                    type="password"
                    placeholder="password"
                    class="input input-bordered"
                    value={field.value}
                  />
                  {field.error && (
                    <div class="label">
                      <span class="label-text-alt text-error">
                        {field.error}
                      </span>
                    </div>
                  )}
                  <label class="label">
                    <a href="#" class="label-text-alt link link-hover">
                      Forgot password?
                    </a>
                  </label>
                </div>
              )}
            </Field>
            <div class="form-control mt-6">
              <button
                class="btn btn-primary"
                type="submit"
                disabled={!loginForm.touched || loginForm.invalid}
              >
                Login
              </button>
            </div>
          </Form>
        </div>
      </div>
    </div>
  );
});

export const head: DocumentHead = {
  title: "Vira login",
  meta: [
    {
      name: "description",
      content: "Vira ecosystem site login page",
    },
  ],
};
